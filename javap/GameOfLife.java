class GameOfLife {
  private String currentGen[][];
  private String nextGen[][];
  final int size = 5;
  final int noOfGen = 4;
    GameOfLife() {
      currentGen = new String[size][size];
      nextGen = new String[size][size];
      initGen();
    }
    private void initGen() {
      int i, j;
      for(i = 0; i < size; i++) {
        for(j = 0; j < size; j++) {
          currentGen[i][j]= ".";
        }
      }
      currentGen[1][2] = "X";
      currentGen[2][2] = "X";
      currentGen[3][2] = "X";
    }
    public String toString() {
      String str = "";
      int i;
      int j;
      for(i = 0; i < size ; i++) {
        for(j = 0; j < size ; j++) {
          str = str + currentGen[i][j];
        }
        str = str + "\n";
      }
      return str;
    }
    public void startStimulation() {
      int i;
      for(i = 1; i <= noOfGen; i++) {
        callNextGen();
        System.out.println(this);
      }
    }
    private void callNextGen() {
      int i, j, count=0;

      for(i = 0; i < size ; i++) {
        for(j = 0; j < size ; j++) {
          count = countNeighbours(i,j);
          //System.out.println(i + " " + j + " " + count);
          if(count == 3) {
            nextGen[i][j] = "X";
          }
          else if(count ==2 && currentGen[i][j] == "X") {
            nextGen[i][j] = "X";
          }
          else {
            nextGen[i][j] = ".";
          }
        }
      }
      copy();
    }
    private void copy() {
      int i, j;
      for(i = 0; i < size ; i++) {
        for(j = 0; j < size ; j++) {
          currentGen[i][j] = nextGen[i][j];
        }
      }
    }
    private int countNeighbours(int row, int column) {
      int i, j, count = 0;
      if(row == 0 || column == 0 || row == size - 1 || column == size - 1) {
        return 0;
      }
      for(i = row -1; i <= row + 1 ; i++) {
        for( j= column - 1; j <= column + 1; j++) {
          if(currentGen[i][j] == "X") {
            count++;
          }
        }
      }
      if(currentGen[row][column] == "X") {
        count--;
      }
      return count;
    }


}