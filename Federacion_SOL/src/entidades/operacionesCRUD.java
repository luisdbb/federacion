package entidades;

public interface operacionesCRUD<T> {
	/***
	 * Este método inserta en la tabla correspondiente de la BD bdfederacion un nuevo registro
	 * @param elemento del tipo que se quiere insertar como nuevo elemento completo (con ID)
	 * @return true si la inserción fue exitosa, false en caso contrario
	 */
	public boolean insertarConID(T elemento);
	
	/***
	 * Este método inserta en la tabla correspondiente de la BD bdfederacion un nuevo registro
	 * @param elemento del tipo que se quiere insertar como nuevo elemento completo (sin ID, que es autocalculado)
	 * @return id del nuevo elemento insertado si tuvo éxito, o -1 en caso contarrio
	 */
	public long insertarSinID(T elemento);
	
}
