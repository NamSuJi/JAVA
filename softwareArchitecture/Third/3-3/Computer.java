package FacadePattern;


public class Computer {
	private CPU cpu;
	private Memory memory;
	private HardDrive hardDrive;
	
	public Computer() {
		this.cpu = new CPU();
		this.memory = new Memory();
		this.hardDrive = new HardDrive();
	}
	public void run() {
		cpu.processData();
		memory.load();
		hardDrive.readData();
	}
}
