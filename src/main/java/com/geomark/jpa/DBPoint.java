package com.geomark.jpa;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POINTS_TABLE_GEO")
public class DBPoint {

        @Id
//        @Type(type="pg-uuid")
        protected long id;

        @Column(name = "name")
        protected String name;

        @Column(name = "lattitude")
        protected double lat;

        @Column(name = "longitude")
        protected double _long;

        @Column(name = "counter")
        protected long counter;

        /**
         * Gets the value of the lat property.
         *
         */
        public double getLat() {
            return lat;
        }

        /**
         * Sets the value of the lat property.
         *
         */
        public void setLat(double value) {
            this.lat = value;
        }

        /**
         * Gets the value of the long property.
         *
         */
        public double getLong() {
            return _long;
        }

        /**
         * Sets the value of the long property.
         *
         */
        public void setLong(double value) {
            this._long = value;
        }

        /**
         * Gets the value of the name property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the counter property.
         *
         */
        public long getCounter() {
            return counter;
        }

        /**
         * Sets the value of the counter property.
         *
         */
        public void setCounter(long value) {
            this.counter = value;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        @Override
        public String toString() {
                return "DBPoint{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", lat=" + lat +
                        ", _long=" + _long +
                        ", counter=" + counter +
                        '}';
        }
}
