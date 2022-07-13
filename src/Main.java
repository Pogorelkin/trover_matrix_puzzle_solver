import service.CalculationService;
import service.RWFileService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        RWFileService rwFileService = new RWFileService();
        List<Boolean> booleans = rwFileService.readStartPosition();
//        booleans.forEach(System.out::println);
        var calculationService = new CalculationService(booleans);
//        CompletableFuture.runAsync(() -> {calculationService.calculateSolution();});
        new Thread(calculationService::calculateSolution).start();
//        new Thread(calculationService::calculateSolution).start();
    }
}
