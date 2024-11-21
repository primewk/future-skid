package me.akaishin.cracked.features.modules.render;

import me.akaishin.cracked.features.modules.*;

public class NoBob extends Module {
    public static NoBob INSTANCE;
    //private final static NoBob INSTANCE = new NoBob();
        public NoBob() {
            super("NoBob", "NoBob xdd", Category.MISC, true, false, true);
            INSTANCE = this;
        }
}

