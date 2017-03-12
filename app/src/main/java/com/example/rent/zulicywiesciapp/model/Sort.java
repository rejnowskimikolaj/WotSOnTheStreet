package com.example.rent.zulicywiesciapp.model;

/**
 * Created by md on 3/12/17.
 */

public enum Sort {
        DATE_ASC("date-asc"),
        PRIORITY_ASC("priority-asc"),
        PRIORITY_DESC("priority-desc");

        private String sort;

        private Sort(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
        }
}
