public class EisenhowerMatrix {
    private static Quadrant quadrant;
    public static Quadrant getQuadrant(Task task){
        if(task.getUrgency().equals(Urgency.High)){
            return Quadrant.FIRST;
        }
        else if(!task.getUrgency().equals(Urgency.High) && task.priority==1){
            return Quadrant.FIRST;
        }
        else if(task.getUrgency().equals(Urgency.MEDIUM))
    }
}
