package templateMethod;

public class ApplicationRunner {
	private Application2 itsApplication = null;
	
	public ApplicationRunner(Application2 app) {
		itsApplication = app;
	}
	
	public void run() {
		itsApplication.init();
		while(!itsApplication.done())
			itsApplication.idle();
		itsApplication.cleanup();
	}
}
