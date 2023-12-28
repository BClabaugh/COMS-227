package hw4;

import api.Point;
import api.PositionVector;
/**
 * 
 * @author Blake Clabaugh
 *
 */
public class CouplingLink extends AbstractLink{
	
	/**
	 * Models a link between exactly two paths.
	 * When the train reaches the endpoint of one of the paths it 
	 * passes to the endpoint of the other path next.
	 */
	
	/**
	 * holds endpoint var
	 */
	private Point endPoint1;
	/**
	 * holds endpoint var
	 */
	private Point endPoint2;
	
	/**
	 * constructor for coupling link
	 * @param highPoint1
	 * @param lowPoint2
	 */
	public CouplingLink(Point highPoint1, Point lowPoint2) {
		endPoint1 = highPoint1;
		endPoint2 = lowPoint2;
	}

	/*
	 * get connected point
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		if(point.equals(endPoint1)) {
			return endPoint2;
		}else if(point.equals(endPoint2)) {	
			return endPoint1;
		}
		return null;
	}

	/**
	 * num of paths
	 */
	@Override
	public int getNumPaths() {
		// TODO Auto-generated method stub
		return 2;
	}
	

	
}
