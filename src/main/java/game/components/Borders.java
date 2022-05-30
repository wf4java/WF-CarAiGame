package game.components;

import models.utils.Line;
import models.utils.Point;

public class Borders {

    public static Line[] init(){
        Point p1 = new Point(291,201);
        Point p2 = new Point(621,164);
        Point p3 = new Point(909,208);
        Point p4 = new Point(996,505);
        Point p5 = new Point(1024,639);
        Point p6 = new Point(745,608);
        Point p7 = new Point(360,656);
        Point p8 = new Point(199,563);
        Point p9 = new Point(284,400);
        Point p10 = new Point(618,71);
        Point p11 = new Point(1026,130);
        Point p12 = new Point(1101,492);
        Point p13 = new Point(1184,654);
        Point p14 = new Point(1073,769);
        Point p15 = new Point(768,710);
        Point p16 = new Point(326,747);
        Point p17 = new Point(77,602);
        Point p18 = new Point(165,409);
        Point p19 = new Point(132,193);
        Point p20 = new Point(244,109);

        Line[] lines = new Line[20];

        lines[0] = new Line(p1, p2);
        lines[1] = new Line(p2, p3);
        lines[2] = new Line(p3, p4);
        lines[3] = new Line(p4, p5);
        lines[4] = new Line(p5, p6);
        lines[5] = new Line(p6, p7);
        lines[6] = new Line(p7, p8);
        lines[7] = new Line(p8, p9);
        lines[8] = new Line(p9, p1);
        lines[9] = new Line(p20, p10);
        lines[10] = new Line(p19, p20);
        lines[11] = new Line(p18, p19);
        lines[12] = new Line(p17, p18);
        lines[13] = new Line(p16, p17);
        lines[14] = new Line(p15, p16);
        lines[15] = new Line(p14, p15);
        lines[16] = new Line(p13, p14);
        lines[17] = new Line(p12, p13);
        lines[18] = new Line(p11, p12);
        lines[19] = new Line(p10, p11);

        return lines;
    }

}
