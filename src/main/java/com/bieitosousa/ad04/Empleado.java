/*
 * The MIT License
 *
 * Copyright 2020 bieito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.bieitosousa.ad04;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bieito
 */
@Entity
@Table(name = "EMPLEADO")

public class Empleado implements Serializable {
    @OneToMany(mappedBy = "empleado")
    private Set<TiendaEmpleado> tiendaEmpleado = new HashSet<TiendaEmpleado>();

    @Id
    @Column(name = "EMPLEADO_id")
      @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    @Column(name = "EMPLEADO_name", nullable=false)
    private String name;
    @Column(name = "EMPLEADO_apellido", nullable=false)
    private String apellido;
    @Column(name = "EMPLEADO_nHoras")
    private float nHoras ;
      @Column(name = "EMPLEADO_nomCompleto", nullable=false, unique = true)
    private String nomCompleto;

    //@OneToMany(mappedBy = "empleado")
    public Set<TiendaEmpleado> getTiendaEmpleado() {
        return tiendaEmpleado;
    }

    public void addTiendaEmpleado(TiendaEmpleado tiendaEmpleado) {
        this.tiendaEmpleado.add(tiendaEmpleado);
    }

    public void setTiendaEmpleado(Set<TiendaEmpleado> tiendaEmpleado) {
        this.tiendaEmpleado = tiendaEmpleado;
    }
public Empleado(){}
    public Empleado(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
        this.nomCompleto=name+apellido;
    }

    public int getId() {
        if (id == -1) {
            cargarId();
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public float getnHoras(Tienda t) {
        cargarHoras(t);
        return nHoras;
    }

    public void setnHoras(float nHoras) {
        this.nHoras = nHoras;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", name=" + name + ", apellidos=" + apellido + '}';
    }

    public String toString(Tienda t) {
        return "Empleado{" + "id=" + id + ", name=" + name + ", apellidos=" + apellido + ", nHoras=" + getnHoras(t) + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return true;
    }

    private void cargarId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarHoras(Tienda t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
