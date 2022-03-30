package artemisLite;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class testScannerTimeout {

	public static void main(String[] args) {
		int userInput;
		Scanner scanner = new Scanner(System.in);
		System.out.println("please select an option from the below\n1.Option 1\n2.Option 2\n3.Option 3");
		getChoiceWithTimeout(5);
		userInput = scanner.nextInt();
		switch (userInput) 
			{
		case 1:
			System.out.println("youve selected option 1");
			break;
		case 2:
			System.out.println("youve selected option 2");
			break;
		case 3:
			System.out.println("youve selected option 3");
			break;
		}

	scanner.close();
	
	}
	
	public static int getChoiceWithTimeout(int range) {
		Callable<Integer> callable = () -> new Scanner(System.in).nextInt();

		ExecutorService service = Executors.newFixedThreadPool(1);

		System.out.println("Enter your choice in 15 seconds :");

		Future<Integer> inputFuture = service.submit(callable);

		try {
			return inputFuture.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new IllegalStateException("Thread was interrupted", e);
		} catch (ExecutionException e) {
			throw new IllegalStateException("Something went wrong", e);
		} catch (TimeoutException e) {
			// tell user they timed out or do something
			throw new IllegalStateException("Timed out! Do you want to continue?");
		} finally {
			service.shutdown();
		}
	}

}
