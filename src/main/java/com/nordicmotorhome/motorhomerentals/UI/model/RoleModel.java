package com.nordicmotorhome.motorhomerentals.UI.model;

//Author : RAP
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
    BOOK_KEEPER {
        public String toString() {
            return "Bogholder";
        }
    }
}
