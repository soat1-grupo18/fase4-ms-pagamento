resource "kubernetes_namespace" "ms_pedido" {
  metadata {
    name = "ms-pedido"
  }
}
