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

public class ArenaClient {
    private static final String GET_HISTORY = "GET_HISTORY";
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 5001;
    private static final int TIMEOUT_MS = 5000;
    private static final String REQUEST_FAILED = "Request failed ";
    private static final String REQUEST_TIMED_OUT = "Request timed out ";
    private static final String RECORDS_SKIPPED = " Records Skipped";

    public void sendBattleResult(BattleResult result) throws IOException {
        try (Socket socket = new Socket(LOCALHOST, PORT)) {
            var outputWriter = createOutputWriter(socket);

            outputWriter.println(result.serialize());

        } catch (IOException e) {
            throw new IOException(REQUEST_FAILED + e.getMessage());
        }
    }

    public List<BattleResult> getBattleHistory() throws IOException {
        List<BattleResult> history = new ArrayList<>();
        int skippedLines = 0;

        try (Socket socket = new Socket(LOCALHOST, PORT)) {
            socket.setSoTimeout(TIMEOUT_MS);
            var outputWriter = createOutputWriter(socket);
            var inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outputWriter.println(GET_HISTORY);

            String line;

            while ((line = inputReader.readLine()) != null) {
                try {
                    history.add(BattleResult.deserialize(line));
                } catch (IllegalArgumentException _) {
                    skippedLines++;
                }
            }

        } catch (SocketTimeoutException e) {
            throw new IOException(REQUEST_TIMED_OUT + e.getMessage());
        } catch (IOException e) {
            throw new IOException(REQUEST_FAILED + e.getMessage());
        }

        if (skippedLines > 0) {
            // Shows if there are any corrupted BattleResults
            System.err.println(skippedLines + RECORDS_SKIPPED);
        }

        return history;
    }

    private PrintWriter createOutputWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
