package gp.Sisteme.Inventario.servicio;

import gp.Sisteme.Inventario.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProduco();

    public Producto buscarProductoPorId(Integer idProducto);

    public Producto gurdadProducto(Producto producto);

    public void eliminarProductoPorId(Integer idProducto);
}
