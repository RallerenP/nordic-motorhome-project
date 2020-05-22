package com.nordicmotorhome.motorhomerentals.MVC.model;

public enum RoleModel {
    SALES_ASSISTANT {
        public String toString() {
            return "Salgs Assistent";
        }
    },
    MECHANIC {
        public String toString() {
            return "Mekaniker";
        }
    },
    CLEANER {
        public String toString() {
            return "Rengøring";
        }
    },
}
