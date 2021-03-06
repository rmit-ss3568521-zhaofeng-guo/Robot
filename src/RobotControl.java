import javax.swing.JOptionPane;

// Instead of hard-coding numbers into each of the loops I've used
// variables to keep track of how the arm is to be moved when moving
// blocks.

// This is by no means the only way of achieving the minimum number of
// moves for the robot - it is intended as an example only and it was not
// used as a guide when the assignments were marked.

class RobotControl
{
	private Robot r;
	public static StringBuilder sb;
	
	public RobotControl(Robot r)
	{
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[])
	{
		 sampleControlMechanism(barHeights, blockHeights);
		// run(barHeights, blockHeights);

	}

	public void sampleControlMechanism(int barHeights[], int blockHeights[])
	{
		// Internally the Robot object maintains the value for Robot height(h),
		// arm-width (w) and picker-depth (d).

		// These values are displayed for your convenience
		// These values are initialised as h=2 w=1 and d=0

		// When you call the methods up() or down() h will be changed
		// When you call the methods extend() or contract() w will be changed
		// When you call the methods lower() or raise() d will be changed

		// sample code to get you started
		// Try running this program with obstacle 555555 and blocks of height
		// 2222 (default)
		// It will work for fisrt block only
		// You are free to introduce any other variables

		int h = 2; // Initial height of arm 1
		int w = 1; // Initial width of arm 2
		int d = 0; // Initial depth of arm 3
        int q1 = 0;
        
		int sourceHt = 12;

		// For Parts (a) and (b) assume all four blocks are of the same height
		// For Part (c) you need to compute this from the values stored in the
		// array blockHeights
		// i.e. sourceHt = blockHeights[0] + blockHeights[1] + ... use a loop!

		int targetCol1Ht = 0; // Applicable only for part (c) - Initially empty
		int targetCol2Ht = 0; // Applicable only for part (c) - Initially empty

		// height of block just picked will be 3 for parts A and B
		// For part (c) this value must be extracing the topmost unused value
		// from the array blockHeights

		int blockHt = 3;

		// clearance should be based on the bars, the blocks placed on them,
		// the height of source blocks and the height of current block

		// Initially clearance will be determined by the blocks at source
		// (3+3+3+3=12)
		// as they are higher than any bar and block-height combined
		int c=0;
		int clearence = 0;
		int i3=0;
		for (int i2=0;i2<blockHeights.length;i2++){
			clearence+=blockHeights[i2];
		}
		System.out.println("clearence="+clearence);
		
		
		int contractAmt = 7;
		int extendAmt = 10;
		int a = 7;
		int th=0;
		int maxth = 0;
		int th1=0;
		int currentBar = 0;
		int botheight=0;
		int max=0;
		int i5=blockHeights.length-1;
		// Raise it high enough - assumed max obstacle = 4 < sourceHt

		// this makes sure robot goes high enough to clear any obstacles
		
            
		
        
		
		for (int i=0;i<blockHeights.length;i++){
		
			
			
			if(clearence>th)
			{max=clearence;}
			else {
				max=th;
			}
		
			
			
			
			
			switch (blockHeights[i5]){
			case 1:
				contractAmt=9;
				botheight=targetCol1Ht;
				q1=1;
				targetCol1Ht++;
				break;

			case 2:
				contractAmt=8;
				botheight=targetCol2Ht;
				q1=2;
				targetCol2Ht=targetCol2Ht+2;
				break;
			case 3:
				contractAmt=a;
				a--;
				botheight=barHeights[currentBar];
				
				currentBar++;
				break;
			}
			System.out.println("blockHeights[i5="+blockHeights[i5]);
			
			
			
			
			c=max + 1 +q1;
			System.out.println("c="+ c );
			System.out.println("q1="+ q1 );
			System.out.println("max1="+ max );
			while (h < max + 1 +q1)
		{
			// Raising 1
			r.up();

			// Current height of arm1 being incremented by 1
			h++;
		}
			
       
       
		System.out.println("Debug 1: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);

		// this will need to be updated each time a block is dropped off
		
		
		
		
		
		
		
		
		

		// Bring arm 2 to column 10
		while (w < extendAmt)
		{
			// moving 1 step horizontally
			r.extend();

			// Current width of arm2 being incremented by 1
			w++;
		}

		System.out.println("Debug 2: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);

		// lowering third arm - the amount to lower is based on current height
		// and the top of source blocks

		// the position of the picker (bottom of third arm) is determined by h
		// and d
		while (h - d > clearence + 1)
		{
			// lowering third arm
			r.lower();

			// current depth of arm 3 being incremented
			d++;
		}

		// picking the topmost block
		r.pick();
		clearence=clearence-blockHeights[i5];
		// topmost block is assumed to be 3 for parts (a) and (b)
		blockHt = 3;

		// When you pick the top block height of source decreases
		sourceHt -= blockHeights[i5];

		// raising third arm all the way until d becomes 0
		while (d > 0)
		{
			r.raise();
			d--;
		}

		System.out.println("Debug 3: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);

		// why not see the effect of changing contractAmt to 6 ?
		

		// Must be a variable. Initially contract by 3 units to get to column 3
		// where the first bar is placed (from column 10)
System.out.println("contractAmt="+contractAmt);
		











        while (contractAmt > 0)
		{
			r.contract();
			contractAmt--;
			w--;
		}
       
		
		
		System.out.println("Debug 4: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);

		// You need to lower the third arm so that the block sits just above the
		// bar
		// For part (a) all bars are initially set to 7
		// For Parts (b) and (c) you must extract this value from the array
		// barHeights
		System.out.println("max="+max);
		
		while (h > max + 1)
		{
			// Raising 1
			r.down();

			// Current height of arm1 being incremented by 1
			h--;
		}
		
		
		
		
		
		
System.out.println("barHeights[currentBar]="+barHeights[currentBar]);
System.out.println("botheight="+botheight);
		// lowering third arm
		while ((h - 1) - d - blockHeights[i5] > botheight)
		{
			
			r.lower();
			d++;
		}
		
	
		for (int i1 =0;i1<currentBar+1;i1++)
		{
			if(barHeights[i1]>th1){
				th1=barHeights[i1];
			}
		
		}
		
		
		th=th1+3;
		
		System.out.println("sourceHt="+sourceHt);
		System.out.println("barHeights[currentBar]="+barHeights[currentBar]);
		System.out.println("th="+th);
		
		System.out.println("Debug 5: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);

		// dropping the block
		r.drop();

		// The height of currentBar increases by block just placed
		

		// raising the third arm all the way
		while (d > 0)
		{
			r.raise();
			d--;
		}
		
		i3++;
		i5--;
		
		System.out.println("Debug 6: height(arm1)= " + h + " width (arm2) = "
				+ w + " depth (arm3) =" + d);
		}
		// This just shows the message at the end of the sample robot run -
		// you don't need to duplicate (or even use) this code in your program.

		JOptionPane.showMessageDialog(null,
				"You have moved one block from source "
						+ "to the first bar position.\n"
						+ "Now you may modify this code or "
						+ "redesign the program and come up with "
						+ "your own method of controlling the robot.",
				"Helper Code Execution", JOptionPane.INFORMATION_MESSAGE);
		// You have moved one block from source to the first bar position.
		// You should be able to get started now.
	}
	
	public void run(int barHeights[], int blockHeights[])
	{
		
	}
}
