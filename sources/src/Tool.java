public enum Tool {
    Save(0),
    Brush(1),
    Rubber(2),
    Bucket(3),
    Spray(4),
    Wand(5),
    ColorPicker(6);
    Tool(int ID){
        code=ID;
    }
    final int code;
}
