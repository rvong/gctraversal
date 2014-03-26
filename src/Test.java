
public class Test {
   
	public static void main(String[] args){
		A a1 = new A();
		GCSimulator.createObject("a1", a1);
		System.out.println("1\ta1:\t" + a1);
		
		A a2 = new A();
		GCSimulator.createObject("a2", a2);
		
		A a3 = new A();
		GCSimulator.createObject("a3", a3);
		System.out.println("2\ta3:\t" + a3);
		
		A a4 = new A();
		GCSimulator.createObject("a4", a4);
		System.out.println("3\ta4:\t" + a4);
		
		B b1 = new B();
		GCSimulator.createObject("b1", b1);
		System.out.println("4\tb1:\t" + b1);
		
		B b2 = new B();
		GCSimulator.createObject("b2", b2);
		System.out.println("5\tb2:\t" + b2);
		
		B b3 = new B();
		GCSimulator.createObject("b3", b3);
		System.out.println("6\tb3:\t" + b3);
		
		B b4 = new B();
		GCSimulator.createObject("b4", b4);
		System.out.println("7\tb4:\t" + b4);
		
		B b5 = new B();
		GCSimulator.createObject("b5", b5);
		System.out.println("8\tb5:\t" + b5);
		
		C c1 = new C();
		GCSimulator.createObject("c1", c1);
		
		C c2 = new C();
		GCSimulator.createObject("c2", c2);
		System.out.println("9\tc2:\t" + c2);
		
		
		
		System.out.println("\nCorrect unreashable objects are:");
		System.out.println("3\ta4:\t" + a4);
		System.out.println("5\tb2:\t" + b2);
		System.out.println("8\tb5:\t" + b5);
		System.out.println("9\tc2:\t" + c2 + "\n\n#################################\n");
		
		
		b2 = b1;
		GCSimulator.assign("b2", "b1", b1);
		
		
		
		
		
		a2.b = b3;
		GCSimulator.writeObject("a2", "b3", "b", a2, b3);
		
		b3 = b1;
		GCSimulator.assign("b3", "b1", b1);
		
		
		
		
		
		a3.b = b4;
		GCSimulator.writeObject("a3", "b4", "b", a3, b4);
		
		c1.a = a3;
		GCSimulator.writeObject("c1", "a3", "a", c1, a3);
		
		a3 = a1;
		GCSimulator.assign("a3", "a1", a1);
		
		b4 = b1;
		GCSimulator.assign("b4", "b1", b1);
		
		
		
		
		a4.b = b5;
		GCSimulator.writeObject("a4", "b5", "b", a4, b5);
		
		c2.a = a4;
		GCSimulator.writeObject("c2", "a4", "a", c2, a4);
		
		a4 = a1;
		GCSimulator.assign("a4", "a1", a1);
		
		b5 = b1;
		GCSimulator.assign("b5", "b1", b1);
		
		c2 = c1;
		GCSimulator.assign("c2", "c1", c1);
		
		
		
		
		
		GCSimulator.gc();
	}
	
	
}


class A{
   B b;	
   public A(){
	   
   }
}

class B{
	
	public B(){
	}

}

class C{
	A a;
	public C(){
		
	}
}


