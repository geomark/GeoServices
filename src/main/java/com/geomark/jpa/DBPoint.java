package com.geomark.jpa;

import javax.persistence.*;
import java.math.BigInteger;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * JPA Entity for Point JPA Representation
 *
 * @author Georgios Markakis
 */
@Entity
@Table(name = "POINTS_TABLE_GEO")
public class DBPoint {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(name = "id")
        private  BigInteger id;

        @Column(name = "name")
        protected String name;

        @Column(name = "lattitude")
        protected Double lat;

        @Column(name = "longitude")
        protected Double _long;

        @Column(name = "counter")
        protected Long counter;

        /**
         * Gets the value of the lat property.
         *
         */
        public Double getLat() {
            return lat;
        }

        /**
         * Sets the value of the lat property.
         *
         */
        public void setLat(Double value) {
            this.lat = value;
        }

        /**
         * Gets the value of the long property.
         *
         */
        public Double getLong() {
            return _long;
        }

        /**
         * Sets the value of the long property.
         *
         */
        public void setLong(Double value) {
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
        public Long getCounter() {
            return counter;
        }

        /**
         * Sets the value of the counter property.
         *
         */
        public void setCounter(Long value) {
            this.counter = value;
        }

        public BigInteger getId() {
                return id;
        }

        public void setId(BigInteger id) {
                this.id = id;
        }

        /**
         * @return
         */
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
