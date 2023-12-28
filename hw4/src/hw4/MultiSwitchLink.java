package hw4;

import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * 
 * @author Blake Clabaugh
 *
 */
/*
 * Creates a new MultiSwitchLink. The given array of point pairs 
 * describes the connection. Each PointPair indicates where the train comes from and goes to.
 */
public class MultiSwitchLink extends AbstractLink{
	
	private int pairs;
	/**
	 * holds point var for pointA1
	 */
	private Point pointA1;
	/**
	 * holds point var for pointB1
	 */
	private Point pointB1;
	/**
	 * holds point var for pointB2
	 */
	private Point pointB2;
	/**
	 * holds point var for pointA2
	 */
	private Point pointA2;
	/**
	 * holds point var for pointB3
	 */
	private Point pointA3;
	/**
	 * holds point var for pointB3
	 */
	private Point pointB3;
	/**
	 * holds point array for the param pointpair
	 */
	private PointPair[] connections;

	
	/**
	 * constructor for multiSwitch
	 * @param connections
	 */
	public MultiSwitchLink(PointPair[] connections) {
		this.connections = connections;
		pairs = connections.length;
		if(connections.length == 1 || connections.length == 2 || connections.length == 3) {
			pointA1 = connections[0].getPointA();
			pointB1 = connections[0].getPointB();
		}else if(connections.length == 2 || connections.length == 3) {
			pointA2 = connections[1].getPointA();
			pointB2 = connections[1].getPointB();
		}else if(connections.length == 3) {
			pointA3 = connections[2].getPointA();
			pointB3 = connections[2].getPointB();
		}
	}
	
	/**
	 * gets the connected point
	 */
	@Override
	public Point getConnectedPoint(Point highPoint) {
		if(highPoint.equals(pointA1)) {
			return pointB1;
		}
		if(highPoint.equals(pointA2)) {
			return pointB2;
		}
		if(highPoint.equals(pointA3)) {
			return pointB3;
		}
		if(highPoint.equals(pointB1)) {
			return pointA1;
		}
		if(highPoint.equals(pointB2)) {
			return pointA2;
		}
		if(highPoint.equals(pointB3)) {
			return pointA3;
		}
		return null;
	}
	
	/**
	 * shifts the points so the position vector continues
	 */
	public void shiftPoints(PositionVector positionVector) {
		
		// TODO Auto-generated method stub
		if(positionVector.getPointB().equals(pointA1)) {
			positionVector.setPointA(pointB1);
			if(pointB1.getPointIndex()== 0) {
			positionVector.setPointB(pointB1.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(pointB1.getPath().getPointByIndex(pointB1.getPath().getNumPoints() - 2));
			}
		}else if(positionVector.getPointB().equals(pointA2)) {
			positionVector.setPointA(pointB2);
			if(pointB2.getPointIndex()== 0) {
			positionVector.setPointB(pointB2.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(pointB2.getPath().getPointByIndex(pointB2.getPath().getNumPoints() - 2));
			}
			
		}else if(positionVector.getPointB().equals(pointA3)) {
				positionVector.setPointA(pointB3);
				if(pointB3.getPointIndex()== 0) {
				positionVector.setPointB(pointB3.getPath().getPointByIndex(1));
				}else {
					positionVector.setPointB(pointB3.getPath().getPointByIndex(pointB3.getPath().getNumPoints() - 2));
				}
				
		}else if(positionVector.getPointB().equals(pointB1)) {
			positionVector.setPointA(pointA1);
			if(pointA1.getPointIndex()== 0) {
			positionVector.setPointB(pointA1.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(pointA1.getPath().getPointByIndex(pointA1.getPath().getNumPoints() - 2));
			}
			
		}else if(positionVector.getPointB().equals(pointB2)) {
			positionVector.setPointA(pointA2);
			if(pointA2.getPointIndex()== 0) {
			positionVector.setPointB(pointA2.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(pointA2.getPath().getPointByIndex(pointA2.getPath().getNumPoints() - 2));
			}
			
		}else if(positionVector.getPointB().equals(pointB3)) {
			positionVector.setPointA(pointA3);
			if(pointA3.getPointIndex()== 0) {
			positionVector.setPointB(pointA3.getPath().getPointByIndex(1));
			}else {
				positionVector.setPointB(pointA3.getPath().getPointByIndex(pointA3.getPath().getNumPoints() - 2));
			}
		}
	}
	
	
	/**
	 * switches connections
	 * @param pointPair
	 * @param i
	 */
	public void switchConnection(PointPair pointPair, int i) {
		// TODO Auto-generated method stub
		pointA2 = pointPair.getPointA();
		pointA3 = pointPair.getPointA();
		pointB2 = pointPair.getPointB();
		pointB3 = pointPair.getPointB();
		pointA1 = pointPair.getPointA();
		pointB1 = pointPair.getPointB();
		
	}
	
	/**
	 * gets num paths
	 */
	public int getNumPaths() {
		return pairs * 2;
	}
}
