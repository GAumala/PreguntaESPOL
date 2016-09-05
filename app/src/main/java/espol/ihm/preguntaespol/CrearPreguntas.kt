package espol.ihm.preguntaespol

import java.util.*

/**
 * Created by USER on 16/08/2016.
 */
class CrearPreguntas {

    companion object {

        fun completarPreguntas(): ArrayList<Pregunta> {

            val pregunta1 = Pregunta("¿Cómo funciona Quicksort?", "Tengo que implementar Quicksort en C pero no entiendo", 10, 1471478400, "Programación")
            val pregunta2 = Pregunta("¿Cuál es la diferencia entre precisión y exactitud?", "Nos mandaron de deber averiguarlo para laboratorio de Física.", 3, 1470355200, "Física")
            val pregunta3 = Pregunta("¿Cómo demostrar la Convolución?", "Demostrar con fotos por favor.", 5, 1470582000, "Ec.Diferenciales")
            pregunta3.scoreUsuario = 1
            val pregunta4 = Pregunta("¿Examen final de Ecología?", "¿Qué toman en el examen final de Ecología?", -4, 1469476800, "Ecología")
            pregunta4.scoreUsuario = -1
            val pregunta5 = Pregunta("¿Qué es una interfaz?", "Nos preguntaron esto en fundamentos, nadie supo la respuesta.", 1, 1470063600, "Programación")
            val pregunta6 = Pregunta("¿Cómo calcular el interés compuesto?", "Necesito la fórmula y un ejemplo por favor.", 5, 1469217600, "Ing.Económica")
            val pregunta7 = Pregunta("¿Qué es herencia y polimorfismo?", "Pregunta de la clase de POO", 6, 1469476800, "Programación")
            val pregunta8 = Pregunta("¿Cómo encontrar el valor de una máscara de subred?", "Necesito un ejemplo de como encontrarla.", 10, 1468612800, "Redes de Comp.")
            val pregunta9 = Pregunta("¿Qué es la desviación estándar?", "Hay lección mañana y necesito saber que es", 2, 1468785600, "Estadística")
            val pregunta10 = Pregunta("¿Cómo encontrar el intervalo de confianza?", "De ser posible incluir un ejemplo", 4, 1468872000, "Estadística")
            val pregunta11 = Pregunta("¿Qué es una mezcla homogénea?", "Pregunta conceptual de química.", 1, 1468699200, "Química")

            val user1 = Usuario("Fernando Campaña", "ferecamp", 5, "Estudiante", 4900)
            val user2 = Usuario("Gabriel Aumala", "gaumala", 5, "Estudiante", 4900)
            val user3 = Usuario("Adriano Pinargote", "apinargo", 3, "Estudiante", 3900)

            val respuesta1a = Respuesta(user1 ,"""
                    |El algoritmo trabaja de la siguiente forma:
                    |Elegir un elemento de la lista de elementos a ordenar, al que llamaremos pivote.
                    |Resituar los demás elementos de la lista a cada lado del pivote, de manera que a un lado queden todos los menores que él, y al otro los mayores.
                    |Los elementos iguales al pivote pueden ser colocados tanto a su derecha como a su izquierda, dependiendo de la implementación deseada. En este momento, el pivote ocupa exactamente el lugar que le corresponderá en la lista ordenada.
                    |La lista queda separada en dos sublistas, una formada por los elementos a la izquierda del pivote, y otra por los elementos a su derecha.
                    |Repetir este proceso de forma recursiva para cada sublista mientras éstas contengan más de un elemento. Una vez terminado este proceso todos los elementos estarán ordenados.
                    """.trimMargin(), 10, 1471478400)
            pregunta1.respuestas.add(respuesta1a)

            val respuesta2a = Respuesta(user2,"""
                    |Precisión se refiere a la dispersión del conjunto de valores obtenidos de mediciones repetidas de una magnitud. Cuanto menor es la dispersión mayor la precisión. Una medida común de la variabilidad es la desviación estándar de las mediciones y la precisión se puede estimar como una función de ella. Es importante resaltar que la automatización de diferentes pruebas o técnicas puede producir un aumento de la precisión. Esto se debe a que con dicha automatización, lo que logramos es una disminución de los errores manuales o su corrección inmediata. No hay que confundir resolución con precisión.
                    |Exactitud se refiere a cuán cerca del valor real se encuentra el valor medido. En términos estadísticos, la exactitud está relacionada con el sesgo de una estimación. Cuanto menor es el sesgo más exacta es una estimación. Cuando se expresa la exactitud de un resultado, se expresa mediante el error absoluto que es la diferencia entre el valor experimental y el valor verdadero.
                    """.trimMargin(), 6, 1470409200)
            pregunta2.respuestas.add(respuesta2a)

            val respuesta3a = Respuesta(user3,"A continuación la demostración de la convolución:", 3, 1470668400) //Aqui se necesita una imagen
            pregunta3.respuestas.add(respuesta3a)

            val respuesta5a = Respuesta(user1, "Interfaz podría hacer referencia al conjunto de métodos que tiene un objeto para poder trabajar con este. Ese conjunto de métodos constituyen la interfaz del objeto (en programación orientada a objetos).", 5, 1470150000)
            val respuesta5b = Respuesta(user2, "Dispositivo capaz de transformar las señales generadas por un aparato en señales comprensibles por otro.", -3, 1470081600)
            pregunta5.respuestas.addAll(listOf(respuesta5a, respuesta5b))

            val respuesta6a = Respuesta(user2, "CF = CI(1+i)^n donde CF es el capital final, CI es el capital inicial, i es la tasas de interés y n es el plazo o número de periodos.", 5, 1469304000)
            pregunta6.respuestas.add(respuesta6a)

            val respuesta7a = Respuesta(user2, """
                    |Herencia en la programación orientada a objetos es la habilidad de extender una funcionalidad existente definiendo una nueva clase que hereda funcionalidad de una clase existente.
                    |Significa literalmente muchas formas. En programación orientada a objetos es una tecnica para optimizar la funcionalidad basada en tipos particulares.
                    |La diferencia entre herencia y polimorfismo es que herencia está relacionada con clases y polimorfismo con métodos.
                    """.trimMargin(), 10, 1469563200)
            pregunta7.respuestas.add(respuesta7a)

            val respuesta8a = Respuesta(user1, "Aquí esta el método para encontrar la máscara:", 4, 1468699200) //Aqui tambien hace falta una imagen
            pregunta8.respuestas.add(respuesta8a)

            val respuesta9a = Respuesta(user2, "La desviación estándar o desviación típica es la raíz cuadrada de la varianza. Es decir, la raíz cuadrada de la media de los cuadrados de las puntuaciones de desviación. La desviación estándar se representa por σ.", 5, 1468785600)
            pregunta9.respuestas.add(respuesta9a)

            val respuesta11a = Respuesta(user3, "Son aquellas mezclas en que sus componentes no se pueden diferenciar a simple vista. Las mezclas homogéneas de líquidos se conocen con el nombre de disoluciones y están constituidas por un soluto y un disolvente, siendo el segundo el que se encuentra en mayor proporción y además suele ser el líquido. Por ejemplo, el agua mezclada con sales minerales o con azúcar, el agua es el disolvente y el azúcar el soluto.", 6, 1468785600)
            pregunta11.respuestas.add(respuesta11a)

            user1.preguntas.add(pregunta2)
            user1.preguntas.add(pregunta3)
            user2.preguntas.add(pregunta1)

            val preguntas = arrayListOf(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10, pregunta11)
            return preguntas


        }
    }

}
