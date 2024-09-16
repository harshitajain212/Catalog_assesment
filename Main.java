import java.util.*;

public class Main {

    
    public static double lagrangeInterpolation(List<Point> points) {
        double c = 0;

        int n = points.size();
        
        for (int i = 0; i < n; i++) {
            double xi = points.get(i).x;
            double yi = points.get(i).y;

            double li = 1; 
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double xj = points.get(j).x;
                    li *= -xj / (xi - xj);
                }
            }

            c += yi * li;
        }

        return c;
    }

    public static int findConstantTerm(Map<String, Object> inputJson) {
        try {
            Map<String, Integer> keys = (Map<String, Integer>) inputJson.get("keys");
            if (keys == null) throw new IllegalArgumentException("Missing 'keys' in input JSON");

            int n = keys.getOrDefault("n", 0);
            int k = keys.getOrDefault("k", 0);

            if (n < k) throw new IllegalArgumentException("'n' must be greater than or equal to 'k'");

            List<Point> points = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                String key = String.valueOf(i);
                if (inputJson.containsKey(key)) {
                    Map<String, String> point = (Map<String, String>) inputJson.get(key);
                    if (point == null) throw new IllegalArgumentException("Invalid format for point with key: " + key);

                    int x = i;
                    int base;
                    int y;

                    try {
                        base = Integer.parseInt(point.get("base"));
                        String valueInBase = point.get("value");
                        y = Integer.parseInt(valueInBase, base);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid number format in point with key: " + key, e);
                    }

                    points.add(new Point(x, y));
                }
            }

            if (points.size() < k) throw new IllegalArgumentException("Not enough points for interpolation");

            double secret = lagrangeInterpolation(points);

            return (int) Math.round(secret);

        } catch (Exception e) {
            System.err.println("Error processing input JSON: " + e.getMessage());
            return Integer.MIN_VALUE; 
        }
    }

    public static void main(String[] args) {
        Map<String, Object> inputJson = new HashMap<>();

        Map<String, Integer> keys = new HashMap<>();
        keys.put("n", 4);
        keys.put("k", 3);
        inputJson.put("keys", keys);

        Map<String, String> point1 = new HashMap<>();
        point1.put("base", "10");
        point1.put("value", "4");
        inputJson.put("1", point1);

        Map<String, String> point2 = new HashMap<>();
        point2.put("base", "2");
        point2.put("value", "111");
        inputJson.put("2", point2);

        Map<String, String> point3 = new HashMap<>();
        point3.put("base", "10");
        point3.put("value", "12");
        inputJson.put("3", point3);

        Map<String, String> point6 = new HashMap<>();
        point6.put("base", "4");
        point6.put("value", "213");
        inputJson.put("6", point6);

        int constantTerm = findConstantTerm(inputJson);
        if (constantTerm != Integer.MIN_VALUE) {


            System.out.println("Constant term (c): " + constantTerm);
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
