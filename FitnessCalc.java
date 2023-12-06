package ia.mario;

public class FitnessCalc {

    private int[][] world;  // Representación del mundo de Mario
    private int marioY;
    private int marioX;

    public FitnessCalc(int[][] world) {
        this.world = world;
        this.marioY = findMarioInitialPositionY();
        this.marioX = 0;
    }

    public int getFitness(Individual individual) {
        char movements[] = individual.genes;
        if (marioY == -1)
            return Integer.MIN_VALUE;

        for (char movement : movements) {
            int move = performMovement(movement);
            if(move == 2)
                return 100 * marioX - 200;
            else if(move == 4)
                return 100 * (world[0].length - 1) - 200;
        }
        // Calcula el fitness basado en la posición final de Mario
        return 100 * marioX;
    }

    private int findMarioInitialPositionY() {
        for (int i = 0; i < world.length; i++) {
            if (world[i][0] == 3) {
                return i;
            }
        }
        // Si no se encuentra la posición inicial de Mario, devuelve un valor predeterminado (puedes ajustarlo según tus necesidades)
        return -1;
    }

    private int performMovement(char movement) {
        int status = 0; // 0 = normal, 1 = parado, 2 = muerto
        switch (movement) {
            case 'D':
                marioX += 1;
                status = performCollisionCheck(0);
                break;
            case 'I':
                marioX -= 1;
                status = performCollisionCheck(1);
                break;
            case 'S':
                marioY -= 1;
                status = performCollisionCheck(2);
                if(status != 0)
                    break;
                marioY -= 1;
                status = performCollisionCheck(2);
                if(status != 0)
                    break;
                marioX += 1;
                status = performCollisionCheck(0);
                if(status != 0)
                    break;
                marioY += 1;
                status = performCollisionCheck(3);
                if(status != 0)
                    break;
                marioY += 1;
                status = performCollisionCheck(3);
                break;
            case 'J':
                marioY -= 1;
                status = performCollisionCheck(2);
                if(status != 0)
                    break;
                marioX += 1;
                status = performCollisionCheck(0);
                if(status != 0)
                    break;
                marioX += 1;
                status = performCollisionCheck(0);
                if(status != 0)
                    break;
                marioX += 1;
                status = performCollisionCheck(0);
                if(status != 0)
                    break;
                marioY += 1;
                status = performCollisionCheck(3);
                break;
        }
        return status; // si mario muere o no
    }
    
    private int performCollisionCheck(int direction) {
        // Chequea si Mario colisionó, se salió de pantalla, o no.
        if (marioY < 0){
            performGravity();
            return 1;
        }
        else if (marioX > world[0].length - 1)
            return 4;          
        int squareCheck = world[marioY][marioX];
        if(squareCheck == 1){
            switch (direction) {
                case 0:
                    marioX -= 1;
                    break;
                case 1:
                    marioX += 1;
                    break;
                case 2:
                    marioY += 1;
                    break;
                default:
                    marioY += 1;;
                    break;
            }
            performGravity();
        }
        if(squareCheck == 2 && direction == 3){
            squareCheck = 1; // 2 significa muerte, pero si vengo por arriba no muero con un enemigo
            performGravity();
        }
        return squareCheck;
    }
    
    private void performGravity() {
        // Aplica gravedad en caso de que Mario caiga
        while (marioY < world.length - 1 && world[marioY + 1][marioX] != 1) {
            marioY += 1;
        }
    }
}