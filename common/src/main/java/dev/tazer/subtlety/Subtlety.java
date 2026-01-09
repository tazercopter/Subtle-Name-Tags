package dev.tazer.subtlety;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subtlety {
    public static final String ID = "subtletly";
    public static final String NAME = "Subtlety";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static void init() {

    }

    public Identifier id(String path) {
        return id(ID, path);
    }

    public Identifier id(String namespace, String path) {
        //? >1.20.1 {
        return Identifier.fromNamespaceAndPath(namespace, path);
        //?} else {
        /*return new ResourceLocation(namespace, path);
         *///?}
    }

    /*
    * TODO:
    * make name tag stealthy
    * make name on hover
    * add tooltips to items when holding
    * check if configs reset magically or not
    */
}
