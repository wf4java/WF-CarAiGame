package game.borders;


import wf_engine.engine.render_components.utils_models.Line;
import wf_engine.engine.render_components.utils_models.Point;

public class Borders {

    public static Point[] points = new Point[20];

    public static Line[] build(){
        setPoints();
        Line[] lines = new Line[20];

        lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[1], points[2]);
        lines[2] = new Line(points[2], points[3]);
        lines[3] = new Line(points[3], points[4]);
        lines[4] = new Line(points[4], points[5]);
        lines[5] = new Line(points[5], points[6]);
        lines[6] = new Line(points[6], points[7]);
        lines[7] = new Line(points[7], points[8]);
        lines[8] = new Line(points[8], points[0]);
        lines[9] = new Line(points[19], points[9]);
        lines[10] = new Line(points[18], points[19]);
        lines[11] = new Line(points[17], points[18]);
        lines[12] = new Line(points[16], points[17]);
        lines[13] = new Line(points[15], points[16]);
        lines[14] = new Line(points[14], points[15]);
        lines[15] = new Line(points[13], points[14]);
        lines[16] = new Line(points[12], points[13]);
        lines[17] = new Line(points[11], points[12]);
        lines[18] = new Line(points[10], points[11]);
        lines[19] = new Line(points[9], points[10]);

        return lines;
    }


    public static void setPoints(){
        points[0] = new Point(291,201);
        points[1] = new Point(621,164);
        points[2] = new Point(909,208);
        points[3] = new Point(996,505);
        points[4] = new Point(1024,639);
        points[5] = new Point(745,608);
        points[6] = new Point(360,656);
        points[7] = new Point(199,563);
        points[8] = new Point(284,400);
        points[9] = new Point(618,71);
        points[10] = new Point(1026,130);
        points[11] = new Point(1101,492);
        points[12] = new Point(1184,654);
        points[13] = new Point(1073,769);
        points[14] = new Point(768,710);
        points[15] = new Point(326,747);
        points[16] = new Point(77,602);
        points[17] = new Point(165,409);
        points[18] = new Point(132,193);
        points[19] = new Point(244,109);

    }

}
