package es.safareyes.swapclosetmarlo.repositorios;

public interface EstadisticaCategoriaMes {
    String getCategoria();
    Integer getAnio();
    Integer getMes();
    String getTipoOperacion();
    Long getTotalOperaciones();
}