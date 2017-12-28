package day22.part1;

import java.awt.Point;

public enum Direction {
  UP {
    @Override
    public Direction rotate(Node value) {
      return isInfected(value) ? RIGHT : LEFT;
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x - 1, actualPosition.y);
    }
  },
  DOWN {
    @Override
    public Direction rotate(Node value) {
      return isInfected(value) ? LEFT : RIGHT;
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x + 1, actualPosition.y);
    }
  },
  LEFT {
    @Override
    public Direction rotate(Node value) {
      return isInfected(value) ? UP : DOWN;
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x, actualPosition.y - 1);
    }
  },
  RIGHT {
    @Override
    public Direction rotate(Node value) {
      return isInfected(value) ? DOWN : UP;
    }

    @Override
    public Point move(Point actualPosition) {
      return new Point(actualPosition.x, actualPosition.y + 1);
    }
  };

  private static boolean isInfected(Node value) {
    return value.infected();
  }

  public abstract Direction rotate(Node value);

  public abstract Point move(Point actualPosition);
}
