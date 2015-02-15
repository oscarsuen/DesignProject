class Game {
	Board b;
	Rule r;

	public Game() {
		this.b = new Board();
		this.r = new Rule();
	}

	public Game(Rule r) {
		this.b = new Board();
		this.r = r;
	}

	public Game(int w, int h, Rule r) {
		this.b = new Board(w,h);
		this.r = r;
	}

	public Game(int w, int h, int[] b, int[] s) {
		this.b = new Board(w,h);
		this.r = new Rule(b,s);
	}

	public void step() {
		b.apply(r);
	}

	public void flip(int i, int j) {
		b.flip(i,j);
	}

	public String toString() {
		return b.toString();
	}

	public int getWidth() {
		return b.getWidth();
	}

	public int getHeight() {
		return b.getHeight();
	}

	public boolean get(int i, int j) {
		return b.get(i,j);
	}

	public boolean[][] getArray() {
		return b.getArray();
	}
}