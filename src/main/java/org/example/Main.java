package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //long start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader("notes.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] a = line.split(";");
            String[][] c = new String[a.length][2];
            for (int i = 0; i < a.length; i++) {
                c[i] = a[i].split(" ");
            }

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < c.length; i++) {
                points.add(new Point(Integer.parseInt(c[i][0]), Integer.parseInt(c[i][1])));
            }
            System.out.println(points.size());
            int count_it = 0;
            long start = System.currentTimeMillis();
            int min = 0; //minByXY(points);
            Point mp = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                count_it += 1;
                Point p = points.get(i);
                if ((p.getX() < mp.getX()) || (p.getX() == mp.getX() && p.getY() < mp.getY())) {
                    min = i;
                    mp = p;
                }
            }
            sortByAngle(points, min);
            int n = 0;
            for (Point point : points) {
                //System.out.println("OX = " + point.getX() + ", OY = " + point.getY());
                if(point.getX() == 0) {
                    n += 1;
                    count_it += 1;
                }
            }
            System.out.println(n);
            Stack<Point> s = new Stack<>();
            Point p0 = points.get(0);
            Point p1 = points.get(n);
            Point p2 = points.get(n+1);
            s.push(p0);
            s.push(p1);
            s.push(p2);
            for (int j = n+2; j < points.size(); j++) {
                boolean stop = false;
                while (!stop && s.size() != 1) {
                    count_it += 1;
                    Point p = s.pop();
                    Point p_p = s.peek();
                    if (right(p, p_p, points.get(j))) {
                        stop = true;
                        s.push(p);
                        s.push(points.get(j));
                    }
                }
            }
            int l = s.size();
            System.out.println("Количество итераций: = " + count_it);
            System.out.println("Точки, входящие в контур выпуклой фигуры: ");
            for (int j = 0; j < l; j++) {
                Point p = s.pop();
                System.out.println("x = " + p.getX() + ", y = " + p.getY());
            }
            //Thread.sleep(1000);
            long finish = System.currentTimeMillis();
            long elapsed = finish - start;
            System.out.println("Время выполнения работы в мс: " + elapsed);
        }
    }

//        public static Stack<Point> grehem (List <Point> points) {
//            int i = minByXY(points);
//            //System.out.println(i);
//            sortByAngle(points, i);
//            int n = 0;
//            for (Point point : points) {
//                //System.out.println("OX = " + point.getX() + ", OY = " + point.getY());
//                if(point.getX() == 0) n += 1;
//            }
//            System.out.println(n);
//            Stack<Point> s = new Stack<>();
//            Point p0 = points.get(0);
//            Point p1 = points.get(n);
//            Point p2 = points.get(n+1);
//            s.push(p0);
//            s.push(p1);
//            s.push(p2);
//            for (int j = n+2; j < points.size(); j++) {
//                boolean stop = false;
//                while (!stop && s.size() != 1) {
//                    Point p = s.pop();
//                    Point p_p = s.peek();
//                    if (right(p, p_p, points.get(j))) {
//                        stop = true;
//                        s.push(p);
//                        s.push(points.get(j));
//                    }
//                }
//            }
//            return s;
//        }

        public static boolean right (Point p1, Point p2, Point p3){
            return ((((p2.getX() - p1.getX()) * (p3.getY() - p2.getY())) - ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()))) > 0);
        }

//        public static int minByXY (List < Point > points) {
//            int min = 0;
//            Point mp = points.get(0);
//            for (int i = 1; i < points.size(); i++) {
//                Point p = points.get(i);
//                if ((p.getX() < mp.getX()) || (p.getX() == mp.getX() && p.getY() < mp.getY())) {
//                    min = i;
//                    mp = p;
//                }
//            }
//            return min;
//        }

        public static void sortByAngle (List < Point > points,int i){
            points.sort(new AngleComparator(points.get(i)));
        }
}