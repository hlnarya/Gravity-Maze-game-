class Player {
	private boolean isLive;
    private int row;
    private int col;
    private int score;
    private int teleportRights;
    private Backpack backpack;

    public Player(int row, int col) {
    	this.isLive = true;
        this.row = row;
        this.col = col;
        this.score = 0;
        this.teleportRights = 3;
        this.backpack = new Backpack(8);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTeleportRights() {
        return teleportRights;
    }

    public void setTeleportRights(int teleportRights) {
        this.teleportRights = teleportRights;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public void moveUp() {
        row--;
    }

    public void moveDown() {
        row++;
    }

    public void moveLeft() {
        col--;
    }

    public void moveRight() {
        col++;
    }

    public void addItemToBackpack(int item) {
    	if(backpack.isFull()) {
      		 backpack.pop();
      		 if (item == 1) {
                   backpack.push(1);
               } else if (item == 2) {
                   backpack.push(2);
               } else if (item == 3) {
                   backpack.push(3);
               } else {
                   System.out.println("Invalid item!");
               }
      		 
      	 }else {
      		 if (item == 1) {
                   backpack.push(1);
               } else if (item == 2) {
                   backpack.push(2);
               } else if (item == 3) {
                   backpack.push(3);
               } else {
                   System.out.println("Invalid item!");
               }
      	 }
   
    }


	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public boolean isDead() {
		return isLive;
	}
}
