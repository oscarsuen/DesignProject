class Test {
	public static void main(String[] args) {
		Board b = new Board();
		b.flip(2,2);
		b.flip(3,2);
		b.flip(4,2);
		b.flip(1,3);
		b.flip(2,3);
		b.flip(3,3);
		System.out.println(b.toString());
		for (int i = 0; i < 5; i++) {
			b.step();
			System.out.println(b.toString());
		}
	}
}