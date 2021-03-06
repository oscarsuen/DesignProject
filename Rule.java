class Rule {
	private boolean[] birth;
	private boolean[] survival;

	public static Rule LIFE = new Rule(new int[]{3}, new int[]{2,3});
	public static Rule REPLICATOR = new Rule(new int[]{1,3,5,7}, new int[]{1,3,5,7});
	public static Rule SEEDS = new Rule(new int[]{2}, new int[]{});
	public static Rule LIFE_WO_DEATH = new Rule(new int[]{3}, new int[]{0,1,2,3,4,5,6,7,8});
	public static Rule LIFE_34 = new Rule(new int[]{3,4}, new int[]{3,4});
	public static Rule DIAMOEBA = new Rule(new int[]{3,5,6,7,8}, new int[]{5,6,7,8});
	public static Rule TWO_BY_TWO = new Rule(new int[]{3,6}, new int[]{1,2,5});
	public static Rule HIGH_LIFE = new Rule(new int[]{3,6}, new int[]{1,2,5});
	public static Rule DAY_NIGHT = new Rule(new int[]{3,6,7,8}, new int[]{3,4,6,7,8});
	public static Rule MORLEY = new Rule(new int[]{3,6,8}, new int[]{3,4,5});

	public Rule() {
		this(new int[]{}, new int[]{});
	}

	public Rule(int[] b, int[] s) {
		birth = new boolean[9];
		survival = new boolean[9];
		for (int i : b) {
			birth[i] = true;
		}
		for (int i : s) {
			survival[i] = true;
		}
	}

	public boolean[] getB() {
		return birth;
	}

	public boolean[] getS() {
		return survival;
	}

	private int i(boolean b) {
		return b ? 1:0;
	}

	public int neighbors(boolean[][] orig, int i, int j, int width, int height) {
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
		} else if (i > 0 && i < width-1 && j == height-1) {
			rtn += i(orig[i-1][j-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][0]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][0]);
			rtn += i(orig[i+1][j-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][0]);
		} else if (i > 0 && i < width-1 && j == 0) {
			rtn += i(orig[i-1][height-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][j+1]);
			rtn += i(orig[i+0][height-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[i+1][height-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][j+1]);
		} else if (i == width-1 && j > 0 && j < height-1) {
			rtn += i(orig[i-1][j-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][j+1]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[0][j-1]);
			rtn += i(orig[0][j+0]);
			rtn += i(orig[0][j+1]);
		} else if (i == 0 && j > 0 && j < height-1) {
			rtn += i(orig[width-1][j-1]);
			rtn += i(orig[width-1][j+0]);
			rtn += i(orig[width-1][j+1]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[i+1][j-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][j+1]);
		} else if (i == width-1 && j == height-1) {
			rtn += i(orig[i-1][j-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][0]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][0]);
			rtn += i(orig[0][j-1]);
			rtn += i(orig[0][j+0]);
			rtn += i(orig[0][0]);
		} else if (i == width-1 && j == 0) {
			rtn += i(orig[i-1][height-1]);
			rtn += i(orig[i-1][j+0]);
			rtn += i(orig[i-1][j+1]);
			rtn += i(orig[i+0][height-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[0][height-1]);
			rtn += i(orig[0][j+0]);
			rtn += i(orig[0][j+1]);
		} else if (i == 0 && j == height-1) {
			rtn += i(orig[width-1][j-1]);
			rtn += i(orig[width-1][j+0]);
			rtn += i(orig[width-1][0]);
			rtn += i(orig[i+0][j-1]);
			rtn += i(orig[i+0][0]);
			rtn += i(orig[i+1][j-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][0]);
		} else if (i == 0 && j == 0) {
			rtn += i(orig[width-1][height-1]);
			rtn += i(orig[width-1][j+0]);
			rtn += i(orig[width-1][j+1]);
			rtn += i(orig[i+0][height-1]);
			rtn += i(orig[i+0][j+1]);
			rtn += i(orig[i+1][height-1]);
			rtn += i(orig[i+1][j+0]);
			rtn += i(orig[i+1][j+1]);
		}
		return rtn;
	}

}