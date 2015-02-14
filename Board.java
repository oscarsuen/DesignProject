class Board {
	private boolean[][] array;
	private int width;
	private int height;

	public Board() {
		this(8,8);
	}
	
	public Board(int w, int h) {
		width = w;
		height = h;
		array = new boolean[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				array[i][j] = false;
			}
		}
	}

	public boolean[][] getArray() {
		return array;
	}

	public void step() {
		boolean[][] orig = copy();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				decide(orig, i, j);
			}
		}
	}

	private boolean[][] copy() {
		boolean[][] copy= new boolean[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				copy[i][j] = array[i][j];
			}
		}
		return copy;
	}

	private void decide(boolean[][] orig, int i, int j) {
		if (orig[i][j]) {
			if (neighbors(orig, i, j) < 2) {
				array[i][j] = false;
			} else if (neighbors(orig, i, j) == 2 || neighbors(orig, i, j) == 3) {
				array[i][j] = true;
			} else if (neighbors(orig, i, j) > 3) {
				array[i][j] = false;
			}
		}
		if (!orig[i][j]) {
			if (neighbors(orig, i, j) == 3) {
				array[i][j] = true;
			}
		}
	}

	public int neighbors(boolean[][] orig, int i, int j) {
		int rtn = 0;
		if (i > 0 && i < width-1 && j > 0 && j < height-1) {
			rtn += i(orig[i-1][j-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][j+1]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[i+1][j-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][j+1]);
		}
		return rtn;
	}

	private int i(boolean b) {
		return b ? 1:0;
	}

	public void flip(int i, int j) {
		array[i][j] = !array[i][j];
	}

	public String toString() {
		String rtn = "";
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				rtn += (i(array[i][j])) + " ";
			}
			rtn += "\n";
		}
		return rtn;
	}
}