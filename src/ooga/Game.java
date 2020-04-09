package ooga;

import java.util.List;

public interface Game {

    public List<Integer> generateRandomOutcome();

    public String checkOutcome(List<Integer> result);

    public int calculatePayoutMultiple(String outcome);
}
