package artemisLite;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ScannerTimeout {

	public int getChoiceWithTimeout(int range) {
		Callable<Integer> callable = () -> new Scanner(System.in).nextInt();

		ExecutorService service = Executors.newFixedThreadPool(1);

		System.out.println("Enter your choice in 15 seconds :");

		Future<Integer> inputFuture = service.submit(callable);

		try {
			return inputFuture.get(15, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new IllegalStateException("Thread was interrupted", e);
		} catch (ExecutionException e) {
			throw new IllegalStateException("Something went wrong", e);
		} catch (TimeoutException e) {
			// tell user they timed out or do something
			throw new IllegalStateException("Timed out! Do something here OP!");
		} finally {
			service.shutdown();
		}
	}
}
