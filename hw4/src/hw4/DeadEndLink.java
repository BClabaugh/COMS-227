package hw4;

import api.Point;
import api.PositionVector;

/**
 * Models a link that connects a single path to nothing.
 *  getConnectedPoint() returns null and shiftPoints() does nothing.
 *  @author Blake Clabaugh
 */
public class DeadEndLink extends AbstractLink{
	
	/**
	 * constructs dead end link
	 */
	public DeadEndLink(){
		
	}

	

	@Override
	public Point getConnectedPoint(Point highPoint) {
		return null;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getNumPaths() {
		return 1;
	}
	
	
	
	
}
