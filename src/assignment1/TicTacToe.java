package assignment1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.rmi.RemoteException;

/**
 * A Tic Tac Toe application. Currently this is a stand-alone application where
 * players take alternating turns using java rmi connection.
 * <p/>
 * Networking application using Java RMI.
 */
public class TicTacToe extends JFrame implements ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7138208255142914277L;
	private static final int BOARD_SIZE = 15;
	private final BoardModel boardModel;
	private final JTable board;
	private final JLabel statusLabel = new JLabel();
	/** Altered fields or additions */
	private final char playerMark;
	private Connection connection;
	private boolean myTurn;
	private boolean finished = false;

	/**
	 * This is not possible to run. Original constructor
	 */
	public TicTacToe() {
		super("TDT4190: Tic Tac Toe");
		boardModel = new BoardModel(BOARD_SIZE);
		board = new JTable(boardModel);
		this.playerMark = 'F';
		setup();
	}

	/**
	 * New constructor when initiated as part of a ConnectionImpl
	 * 
	 * @param client Connection to other player
	 * @param server Server - Server host or client
	 */
	public TicTacToe(Connection connection, boolean server, char playerMark) {
		super("TicTacToe" + (server ? ": Server" : ": Client"));
		this.connection = connection;
		this.playerMark = playerMark;
		if (server) {
			this.myTurn = true;
			setStatusMessage("It is your turn.");
		} else {
			this.myTurn = false;
			setStatusMessage("It is your opponent's turn.");
		}

		boardModel = new BoardModel(BOARD_SIZE);
		board = new JTable(boardModel);
		setup();
	}

	/**
	 * Moved initial code from constructor to help-function.
	 */
	private void setup() {
		board.setFont(board.getFont().deriveFont(25.0f));
		board.setRowHeight(30);
		board.setCellSelectionEnabled(true);
		for (int i = 0; i < board.getColumnCount(); i++)
			board.getColumnModel().getColumn(i).setPreferredWidth(30);
		board.setGridColor(Color.BLACK);
		board.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer dtcl = new DefaultTableCellRenderer();
		dtcl.setHorizontalAlignment(SwingConstants.CENTER);
		board.setDefaultRenderer(Object.class, dtcl);
		board.getSelectionModel().addListSelectionListener(this);
		board.getColumnModel().getSelectionModel()
				.addListSelectionListener(this);

		statusLabel.setPreferredSize(new Dimension(statusLabel
				.getPreferredSize().width, 40));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(board, BorderLayout.CENTER);
		contentPane.add(statusLabel, BorderLayout.SOUTH);
		pack();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		int centerX = (int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - getSize().width) / 2;
		int centerY = (int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight() - getSize().height) / 2;
		setLocation(centerX, centerY);
		setVisible(true);
	}

	/**
	 * Set status message for the game
	 * @param status Status Message
	 */
	void setStatusMessage(String status) {
		statusLabel.setText(status);
	}


	/**
	 * Modified function from original.
	 */
	public void valueChanged(ListSelectionEvent e) {
		// Checks whether or not it is my turn or if game is finished
		if (!this.myTurn || this.finished)
			return;
		if (e.getValueIsAdjusting())
			return;
		int x = board.getSelectedColumn();
		int y = board.getSelectedRow();
		if (x == -1 || y == -1 || !boardModel.isEmpty(x, y))
			return;
		try {
			// Returns true if a win has been detected locally
			if (boardModel.setCell(x, y, playerMark)) {
				// Returns true if the other player agrees.
				if (this.connection.registerTurn(x, y, this.playerMark)) {
					setStatusMessage("Player " + playerMark + " won!");
					gameFinished();
					this.connection.hasWon(this.playerMark);
					return;
				} else {
					// This shouldn't be used at all
				}
			}
			// Register action to other player
			this.connection.registerTurn(x, y, this.playerMark);
			// Indicate remotely that this player is finished
			this.connection.nextPlayer();
			// Change player locally
			changePlayer();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Change player
	 */
	public void changePlayer() {
		// Flip boolean value
		this.myTurn = !this.myTurn;
		// Change status message for game
		setStatusMessage("It is your " + (this.myTurn ? "" : "opponent's")
				+ " turn.");
	}

	// Simple get-function
	public BoardModel getBoardModel() {
		return this.boardModel;
	}

	// Function to be called when game is finished
	public void gameFinished() {
		this.finished = true;
	}
}
