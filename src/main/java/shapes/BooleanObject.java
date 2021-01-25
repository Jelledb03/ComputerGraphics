package shapes;

import internal.*;

import java.util.ArrayList;

public class BooleanObject {
    private Object left;
    private Object right;
    private BooleanObjectType type;
    private boolean left_inside = false;
    private boolean right_inside = false;
    private boolean comb_inside = false;
    private ArrayList<Hit> hit_times;

    public BooleanObject(Object left, Object right, BooleanObjectType type) {
        this.left = left;
        this.right = right;
        this.type = type;
        this.hit_times = new ArrayList<>();
    }

    public Object get_left() {
        return left;
    }

    public void set_left(Object left) {
        this.left = left;
    }

    public Object get_right() {
        return right;
    }

    public void set_right(Object right) {
        this.right = right;
    }

    public BooleanObjectType get_type() {
        return type;
    }

    public void set_type(BooleanObjectType type) {
        this.type = type;
    }

    public boolean is_left_inside() {
        return left_inside;
    }

    public void set_left_inside(boolean left_inside) {
        this.left_inside = left_inside;
    }

    public boolean is_right_inside() {
        return right_inside;
    }

    public void set_right_inside(boolean right_inside) {
        this.right_inside = right_inside;
    }

    public boolean is_comb_inside() {
        return comb_inside;
    }

    public void set_comb_inside(boolean comb_inside) {
        this.comb_inside = comb_inside;
    }

    public ArrayList<Hit> get_hit_times() {
        return hit_times;
    }

    public void set_hit_times(ArrayList<Hit> hit_times) {
        this.hit_times = hit_times;
    }

    public void update_boolean_object() {
        //Going to create its own hit times list and delete the hit times lists of left and right objects.
        //We need to do different things according to the type of the boolean object so we create a switch case
        boolean finished = false;
        switch (type) {
            case UNION:
                while (!finished) {
                    //Checks for the lowest hit time between the two object lists and act accordingly
                    //There are hit times in both objects
                    if (left.get_hit_times().size() != 0 && right.get_hit_times().size() != 0) {
                        Hit lowest_left_hit = left.get_hit_times().get(0);
                        Hit lowest_right_hit = right.get_hit_times().get(0);
                        if (lowest_left_hit.get_t_hit() < lowest_right_hit.get_t_hit()) {
                            //left is lowest
                            left_inside = !lowest_left_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside || right_inside;
                            if (comb_inside != temp_comb_inside) {
                                hit_times.add(lowest_left_hit);
                                comb_inside = temp_comb_inside;
                            }
                            left.get_hit_times().remove(0);
                        } else {
                            //right is lowest
                            right_inside = !lowest_right_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside || right_inside;
                            if (comb_inside != temp_comb_inside) {
                                hit_times.add(lowest_right_hit);
                                comb_inside = temp_comb_inside;
                            }
                            right.get_hit_times().remove(0);
                        }
                    } else if (right.get_hit_times().size() != 0) {
                        //left hit times list is consumed
                        //add remaining of right hit times to the boolean object
                        hit_times.addAll(right.get_hit_times());
                        right.get_hit_times().clear();
                        finished = true;
                    } else if (left.get_hit_times().size() != 0) {
                        //right hit times list is consumed
                        //add remaining of left hit times to the boolean object
                        hit_times.addAll(left.get_hit_times());
                        left.get_hit_times().clear();
                        finished = true;
                    } else {
                        finished = true;
                    }
                }
                break;
            case INTERSECTION:
                while (!finished) {
                    //Checks for the lowest hit time between the two object lists and act accordingly
                    //There are hit times in both objects
                    if (left.get_hit_times().size() != 0 && right.get_hit_times().size() != 0) {
                        Hit lowest_left_hit = left.get_hit_times().get(0);
                        Hit lowest_right_hit = right.get_hit_times().get(0);
                        if (lowest_left_hit.get_t_hit() < lowest_right_hit.get_t_hit()) {
                            //left is lowest
                            left_inside = lowest_left_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside && right_inside;
                            if (comb_inside != temp_comb_inside) {
                                //hit_times.add(lowest_left_hit);
                                comb_inside = temp_comb_inside;
                            }
                            left.get_hit_times().remove(0);
                        } else {
                            //right is lowest
                            right_inside = lowest_right_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside && right_inside;
                            if (comb_inside != temp_comb_inside) {
                                hit_times.add(lowest_right_hit);
                                comb_inside = temp_comb_inside;
                            }
                            right.get_hit_times().remove(0);
                        }
                    } else if (right.get_hit_times().size() != 0) {
                        //left hit times list is consumed
                        //add remaining of right hit times to the boolean object
                        hit_times.addAll(right.get_hit_times());
                        right.get_hit_times().clear();
                        finished = true;
                    } else if (left.get_hit_times().size() != 0) {
                        //right hit times list is consumed
                        //add remaining of left hit times to the boolean object
                        //hit_times.addAll(left.get_hit_times());
                        left.get_hit_times().clear();
                        finished = true;
                    } else {
                        finished = true;
                    }
                }
                break;
            case DIFFERENCE:
                while (!finished) {
                    //Checks for the lowest hit time between the two object lists and act accordingly
                    //There are hit times in both objects
                    if (left.get_hit_times().size() != 0 && right.get_hit_times().size() != 0) {
                        Hit lowest_left_hit = left.get_hit_times().get(0);
                        Hit lowest_right_hit = right.get_hit_times().get(0);
                        if (lowest_left_hit.get_t_hit() < lowest_right_hit.get_t_hit()) {
                            //left is lowest
                            left_inside = lowest_left_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside && !right_inside;
                            if (comb_inside != temp_comb_inside) {
                                hit_times.add(lowest_left_hit);
                                comb_inside = temp_comb_inside;
                            }
                            left.get_hit_times().remove(0);
                        } else {
                            //right is lowest
                            right_inside = lowest_right_hit.isEntering();
                            //With union the comb_inside is left_inside || right_inside
                            //After assigning check if comb inside changes if so add it to hit_times
                            boolean temp_comb_inside = left_inside && !right_inside;
                            if (comb_inside != temp_comb_inside) {
                                //hit_times.add(lowest_right_hit);
                                comb_inside = temp_comb_inside;
                            }
                            right.get_hit_times().remove(0);
                        }
                    } else if (right.get_hit_times().size() != 0) {
                        //left hit times list is consumed
                        //add remaining of right hit times to the boolean object
                        //hit_times.addAll(right.get_hit_times());
                        right.get_hit_times().clear();
                        finished = true;
                    } else if (left.get_hit_times().size() != 0) {
                        //right hit times list is consumed
                        //add remaining of left hit times to the boolean object
                        hit_times.addAll(left.get_hit_times());
                        left.get_hit_times().clear();
                        finished = true;
                    } else {
                        finished = true;
                    }
                }
                break;
            default:
                break;
        }
    }
}
