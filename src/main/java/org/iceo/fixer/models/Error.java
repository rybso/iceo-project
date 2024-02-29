package org.iceo.fixer.models;

import lombok.Data;

@Data
public class Error {

    public int code;
    public String type;
    public String info;

}