package day19;

import java.awt.Point;

public enum Direction {
  DOWN {
    @Override
    public Point move(Point point) {
      return new Point(point.x + 1, point.y);
    }
  },
  UP {
    @Override
    public Point move(Point point) {
      return new Point(point.x - 1, point.y);
    }
  },
  LEFT {
    @Override
    public Point move(Point point) {
      return new Point(point.x, point.y - 1);
    }
  },
  RIGHT {
    @Override
    public Point move(Point point) {
      return new Point(point.x, point.y + 1);
    }
  };

  public abstract Point move(Point point);
}
