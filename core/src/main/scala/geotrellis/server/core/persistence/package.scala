package geotrellis.server.core

import com.azavea.maml.ast.Expression
import cats._
import cats.effect.IO
import io.circe._
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap

import java.util.UUID


package object persistence {

  implicit val inMemMamlStore: MamlStore[ConcurrentLinkedHashMap[UUID, Expression]] =
    new MamlStore[ConcurrentLinkedHashMap[UUID, Expression]] {
      def getMaml(store: ConcurrentLinkedHashMap[UUID, Expression], key: UUID): IO[Option[Expression]] =
        IO.pure { Option(store.get(key)) }

      def putMaml(store: ConcurrentLinkedHashMap[UUID, Expression], key: UUID, maml: Expression): IO[Unit] =
        IO.pure { store.put(key, maml) }
    }

}

