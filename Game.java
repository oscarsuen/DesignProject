class Game {
	Board b;
	Rule r;

	public Game() {
		b = new Board();
		r = new Rule();
	}

	public Game(Rule r) {
		b = new Board();
		this.r = r;
	}

	public Game(int w, int h, int[] b, int[] s) {
		this.r = new Rule(b,s);
		this.b = new Board(w,h);
		
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
}