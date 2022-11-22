package narr

import narr.native.NArr

import scala.collection.ArrayOps
import scala.compiletime.erasedValue
import scala.reflect.ClassTag
import scala.language.implicitConversions

package object native {

  type ByteArray = Array[Byte]
  type ShortArray = Array[Short]
  type IntArray = Array[Int]
  type FloatArray = Array[Float]
  type DoubleArray = Array[Double]
  type NativeArray[T] = Array[T]

  type NArray[T] = Array[T]

  type NArr[T] = Array[T]

  inline def makeNativeArrayOfSize[A:ClassTag](n:Int):NativeArray[A] = new Array[A](n)

  object NArray {
    export Array.*
  }

  object Extensions {

    @inline implicit def refNArrayOps[AT <: NativeTypedArray](xs:AT): ArrayOps[_] = xs match {
      case ab: ByteArray => new ArrayOps(ab)
      case as: ShortArray => new ArrayOps(as)
      case ai: IntArray => new ArrayOps(ai)
      case af: FloatArray => new ArrayOps(af)
      case ad: DoubleArray => new ArrayOps(ad)
      case _ => new ArrayOps[ArrayElementType[AT]](xs.asInstanceOf[Array[ArrayElementType[AT]]])
    }

  }

}