package battlearena.client;

import battlearena.model.BattleResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArenaClient {
    public static final String GET_HISTORY = "GET_HISTORY";
    public static final String LOCALHOST = "localhost";
    public static final int PORT = 5000;
    private static final int TIMEOUT_MS = 5000;
    public static final String REQUEST_FAILED = "Request failed ";
    public static final String REQUEST_TIMED_OUT = "Request timed out ";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void sendBattleResult(BattleResult result) throws IOException {
        try (Socket socket = new Socket(LOCALHOST, PORT)) {
            var outputWriter = createOutputWriter(socket);

            outputWriter.println(result.serialize());

        } catch (IOException e) {
            throw new IOException(REQUEST_FAILED + e.getMessage());
        }
    }

    public void sendBattleResultAsync(BattleResult result) {
        executor.submit(() -> {
            try {
                sendBattleResult(result);
            } catch (IOException e) {
                System.err.println(REQUEST_FAILED + e.getMessage());
            }
        });
    }

    public List<BattleResult> getBattleHistory() throws IOException {
        List<BattleResult> history = new ArrayList<>();

        try (Socket socket = new Socket(LOCALHOST, PORT)) {
            socket.setSoTimeout(TIMEOUT_MS);
            var outputWriter = createOutputWriter(socket);
            var inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outputWriter.println(GET_HISTORY);

            String line;

            while ((line = inputReader.readLine()) != null) {
                history.add(BattleResult.deserialize(line));
            }

        } catch (SocketTimeoutException e) {
            throw new IOException(REQUEST_TIMED_OUT + e.getMessage());
        } catch (IOException e) {
            throw new IOException(REQUEST_FAILED + e.getMessage());
        }

        return history;
    }

    private PrintWriter createOutputWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
