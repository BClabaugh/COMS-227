package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Blake Clabaugh
 */
public class ThreeCushion {

	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	
	/**
	 * holds whos shooting at table
	 */
	private PlayerPosition playerPossesion;
	
	
	/**
	 * holds the cue ball of player A
	 */
	private BallType playerABall;
	
	
	/**
	 * holds cue ball of player B
	 */
	private BallType playerBBall;
	
	/**
	 * holds score of player A
	 */
	private int playerAScore = 0;
	
	
	/**
	 * holds score of player b
	 */
	private int playerBScore = 0;
	
	/**
	 * holds the inning
	 */
	private int inning = 1;
	
	/**
	 * if opposing cue abll is hit during a shot then this reutrns true
	 */
	private boolean opposingCueBallHit;
	
	/**
	 * tests to see if a bankshot happens
	 */
	private int bankShot;
	
	/**
	 * sees if the break shot has happened or not
	 */
	private boolean breakShot;
	/**
	 * sees if the break shot occured
	 */
	private boolean gameOver = false;
	
	/**
	 * sees if inning has started
	 */
	private boolean inningStarted;
	
	/**
	 * sees if shot has started
	 */
	private boolean shotStarted;
	
	/**
	 * holds the amount of points needed to win
	 */
	private int pointsToWin;  
	
	/**
	 * keeps track of how many cushions are hit per inning
	 */
	private int cushionsHit;
	
	/**
	 * sees if the red ball was ever hit
	 */
	private boolean redBallHit;
	
	/**
	 * returns true if the shot was successful and the person should get a point
	 */
	private boolean successfulShot = false;

	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the predetermined number 
	 * of points required to win the game. The inning count starts at 1.
	 * @param lagWinner either A or B
	 * @param pointsToWin the number of point a player needs to make the game end
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		playerPossesion = lagWinner;
		this.pointsToWin = pointsToWin;
	}
	
	/**
	 * public void lagWinnerChooses​(boolean selfBreak, BallType cueBall)
	 * Sets whether the player that won the lag chooses to break (take first shot), or chooses
	 * the other player to break. If this method is called more than once it should have no
	 * effect. In other words, the lag winner can only choose these options once and may
	 * not change their mind afterwards.
	 * @param selfBreak if true the lag winner chooses to take the break shot
	 * @param cueBall the lag winners chosen cue ball (either WHITE or YELLOW)
	 */

	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		if(gameOver!=true && inning == 1) {
			if(playerPossesion == PLAYER_A) {
				playerABall = cueBall;
				if(cueBall == BallType.YELLOW) {
					playerBBall = BallType.WHITE;
				}else {
					playerBBall = BallType.YELLOW;
				}
			}
			else{
				playerBBall = cueBall;
				if(cueBall == BallType.YELLOW) {
					playerABall = BallType.WHITE;
				}else {playerABall = BallType.YELLOW;
				}
			}
			if(playerPossesion == PLAYER_A && selfBreak == true) {
				breakShot = true;
				
			}else if(playerPossesion == PLAYER_B && selfBreak == true){
				breakShot = true;
				
			}else if(playerPossesion == PLAYER_A && selfBreak == false){
				playerPossesion = PLAYER_B;
				breakShot = true;
							
			}else if(playerPossesion == PLAYER_B && selfBreak == false){
				playerPossesion = PLAYER_A;
				breakShot = true;
					
			}
	
	
		}
	}
		
				
		

	
	
	/**
	 * Indicates the cue stick has struck the given ball. If a shot has not already begun, 
	 * indicates the start of a new shot. If this method is called while a shot is still 
	 * in progress (i.e., endShot() has not been called for the previous shot), 
	 * the player has committed a foul (see the method foul()). Also, if the player 
	 * strikes anything other than their own cue ball, they committed a foul.
	 * Calling this method signifies both the start of a shot and the start of an inning,
	 *  assuming a shot or inning has not already begun, respectively.
	 *  Even if a foul has been committed, calling this method is 
	 *  considered the start of a shot. That includes even the case when the 
	 *  player strikes a ball other than their own cue ball. It is expected that the
	 *   endShot() method will be called in any case to indicate the end of the shot.
	 *   No play can begin until the lag player has chosen who will break 
	 *   (see lagWinnderChooses). If this method is called before 
	 *   the break is chosen, it should do nothing.
	 *   If this method is called after the game has ended, it should do nothing.
	 */
	
	public void cueStickStrike(BallType ball) {
		if(gameOver == false && shotStarted == false) {
			
			if(getInningPlayer()==PLAYER_A && playerABall == ball) {
			
			
				shotStarted = true;
				inningStarted = true;
				bankShot = 0;
				cushionsHit = 0;
				breakShot = false;
			}else if (getInningPlayer()==PLAYER_B && playerBBall == ball) {
				shotStarted = true;
				inningStarted = true;
				bankShot = 0;
				cushionsHit = 0;
				breakShot = false;
			}else{foul();
			}
		}
	}
	
	
	//player that has ball rn is gonna hit seleced ball are they hitting theres
	//if cue balll doesnt match then its a foul
	
	
	/**
	 * Indicates the player's cue ball has struck the given ball.
     *A ball strike cannot happen before a stick strike. If this method is 
     *called before the start of a shot (i.e., cueStickStrike() is called), it should do nothing.
     *If this method is called after the game has ended, it should do nothing.
	 * @param ball
	 */
	public void cueBallStrike(BallType ball) {
		if(shotStarted == true && gameOver == false && inningStarted == true) {
			if(ball == getCueBall()){
				foul();
			}
			
			if(ball == BallType.RED ) {
				redBallHit = true;
				if(redBallHit == true && opposingCueBallHit == true) {
					if(cushionsHit <= 3) {
						successfulShot = true;
				}
			}else {
				opposingCueBallHit = true;
				if(redBallHit == true && opposingCueBallHit == true) {
					if(cushionsHit <= 3) {
						successfulShot = true;
					}
				}
			
			}
		
			
			}
		}
	}
	
	/**
	 * Indicates the given ball has impacted the given cushion.
	 *A cushion impact cannot happen before a stick strike. If this method is 
	 *called before the start of a shot (i.e., cueStickStrike() is called), it should do nothing.

	 *If this method is called after the game has ended, it should do nothing.
	 */

	public void cueBallImpactCusion() {
		if(shotStarted == true && isGameOver() == false && isInningStarted() == true) {
			cushionsHit++;
			bankShot++;
		}
	}
	
	
	/**
	 * Indicates that all balls have stopped motion. If the shot was valid and no
	 *  foul was committed, the player scores 1 point.
	 *The shot cannot end before it has started with a call to cueStickStrike. If this
     * method is called before cueStickStrike, it should be ignored.

	 *A shot cannot end before the start of a shot. If this method is called before
	 *the start of a shot (i.e., cueStickStrike() is called), it should do nothing.

	 *If this method is called after the game has ended, it should do nothing.
	 */
	public void endShot() {
		if(shotStarted == true && gameOver == false && isInningStarted() == true) {
			if(getInningPlayer() == PLAYER_A && successfulShot == true ) {
				playerAScore++;
				shotStarted = false;	
				cushionsHit = 0;
				
			}else if(getInningPlayer() == PLAYER_B && successfulShot == true) { 
				playerBScore++;
				shotStarted = false;
				cushionsHit = 0;
				
			}
			
			if(shotStarted == true && gameOver == false && successfulShot == false ) {
				shotStarted = false;
				cushionsHit = 0;
				inning++;
				inningStarted = false;
				if(getInningPlayer() == PLAYER_A) {
					playerPossesion = PLAYER_B;
					
				}else {
					playerPossesion = PLAYER_A;
				}
				
			}
			if(playerAScore == pointsToWin || playerBScore == pointsToWin) {
				gameOver = true;}
		}
	}
	
	
	
	/**
	 * A foul immediately ends the player's inning, even if the current shot has not
	 *  yet ended. When a foul is called, the player does not score a point for the shot.
	 *  A foul may also be called before the inning has started. In that case the player 
	 *  whose turn it was to shoot has their inning forfeited and the inning count is increased by one.
	 *  No foul can be called until the lag player has chosen who will break 
	 *  (see lagWinnerChooses()). If this method is called before 
	 *  the break is chosen, it should do nothing.
	 *  If this method is called after the game has ended, it should do nothing.
	 */
	public void foul() {
		if(breakShot == false && gameOver == false) {
			shotStarted = false;
			cushionsHit = 0;
			inning++;
			inningStarted = false;
			if(getInningPlayer() == PLAYER_A) {
				playerPossesion = PLAYER_B;
				
			}else {
				playerPossesion = PLAYER_A;
			}
			
		}
	}
	
	
	
	/**
	 * Gets the number of points scored by Player A.
	 * @return the number of points
	 */
	public int getPlayerAScore() {
		return playerAScore;
	}
	
	
	/**
	 * Gets the number of point scored by Player B
	 * @return the number of points
	 */
	public int getPlayerBScore() {
		return playerBScore;
	}
	
	
	/**
	 * Gets the inning number. The inning count starts at 1.
	 * @return the inning number
	 */
	public int getInning() {
		return inning;
	}
	
	
	/**
	 * Gets the cue ball of the current player. If this method is called in between innings,
	 *  the cue ball should be the for the player of the upcoming inning. If this method is called 
	 *  before the lag winner has chosen a cue ball, the cue ball is undefined
	 *   (this method may return anything).
	 * @return the players cue ball
	 */
	public BallType getCueBall() {
		if(playerPossesion == PLAYER_A) {
			return playerABall;
		}else
			return playerBBall;
	}
	
	
	
	/**
	 * Gets the current player. If this method is called in between innings, the current player 
	 * is the player of the upcoming inning. If this method is called before the lag winner
	 *  has chosen to break, the current player is undefined (this method may return anything).
	 * @return the current player
	 */
	
	public PlayerPosition getInningPlayer() {
		if(playerPossesion == PLAYER_A) {
			return PLAYER_A;
		}else {
			return PLAYER_B;
		}
		
	}
	
	
	
	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of the game).
	 * @return true if this is the break shot, false otherwise
	 */
	
	public boolean isBreakShot() {
		if(breakShot == true) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Returns true if and only if the most recently completed shot was a bank shot. A bank shot is when the 
	 * cue ball impacts the cushions at least 3 times and then strikes both object balls.
	 * @return true if shot was a bank shot, false otherwise
	 */
	
	public boolean isBankShot() {
		if(bankShot >= 2) {
			return true;
		}return false;
	}
	
	
	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended (see endShot()).
	 * @return  true if the shot has been started, false otherwise
	 */
	public boolean isShotStarted() {
		return shotStarted;
	}
	
	
	/**
	 * Returns true if the shooting player has taken their first shot of the inning. The inning 
	 * starts at the beginning of the shot (i.e., the shot may not have ended yet).
	 * @return true if the inning has started, false otherwise
	 */
	public boolean isInningStarted() {
		if(inningStarted == true) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Returns true if the game is over (i.e., one of the players has 
	 * reached the designated number of points to win).
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	
	
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}

