class Test {
	public static void main(String[] args) {
		Game g = new Game(Rule.LIFE);
		g.flip(2,2);
		g.flip(3,2);
		g.flip(4,2);
		g.flip(1,3);
		g.flip(2,3);
		g.flip(3,3);
		System.out.println(g.toString());
		for (int i = 0; i < 5; i++) {
			g.step();
			System.out.println(g.toString());
		}
	}
}