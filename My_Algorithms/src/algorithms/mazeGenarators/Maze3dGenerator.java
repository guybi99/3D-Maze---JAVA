package algorithms.mazeGenarators;

public interface Maze3dGenerator {
	Maze3d generate(int levels, int rows, int colms);
	String measureAlgorithmTime(int levels, int rows, int colms);
}
