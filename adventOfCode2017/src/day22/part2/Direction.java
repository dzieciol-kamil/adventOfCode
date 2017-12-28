package day22.part2;

import java.awt.Point;

public enum Direction {
  UP {
    @Override
    public Direction rotate(day22.part2.Node value) {
      return getDirection(value, LEFT, UP, RIGHT, DOWN);
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x - 1, actualPosition.y);
    }
  },
  DOWN {
    @Override
    public Direction rotate(day22.part2.Node value) {
      return getDirection(value, RIGHT, DOWN, LEFT, UP);
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x + 1, actualPosition.y);
    }
  },
  LEFT {
    @Override
    public Direction rotate(day22.part2.Node value) {
      return getDirection(value, DOWN, LEFT, UP, RIGHT);
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x, actualPosition.y - 1);
    }
  },
  RIGHT {
    @Override
    public Direction rotate(day22.part2.Node value) {
      return getDirection(value, UP, RIGHT, DOWN, LEFT);
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x, actualPosition.y + 1);
    }
  };

  private static Direction getDirection(Node value,
                                        Direction clean,
                                        Direction weakened,
                                        Direction infected,
                                        Direction flagged) {
    switch (value) {
      case CLEAN:
        return clean;
      case WEAKENED:
        return weakened;
      case INFECTED:
        return infected;
      case FLAGGED:
        return flagged;
    }
    throw new RuntimeException("Shall not pass");
  }

  private static boolean isInfected(day22.part2.Node value) {
    return value.infected();
  }

  public abstract Direction rotate(day22.part2.Node value);

  public abstract Point move(Point actualPosition);
}
