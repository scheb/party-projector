package de.christianscheb.partyprojector.model;

import org.ini4j.Wini;

public interface IniSerializable {

    void serialize(Wini ini);

    void unserialize(Wini ini);
}
