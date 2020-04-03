package FacadePattern;

public class Computer {
	private CPU cpu;
	private Memory memory;
	private HardDrive hardDrive;
	private Done done;
	
	public Computer() {
		this.cpu = new CPU();
		this.memory = new Memory();
		this.hardDrive = new HardDrive();
		this.done = new Done();
	}
	public void run() {
		cpu.processData();
		memory.load();
		hardDrive.readData();
		done.notice();
	}
}
