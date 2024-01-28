data "aws_eks_cluster" "fiap_store" {
  name = "fiap-cluster"
}

data "aws_eks_cluster_auth" "fiap_store" {
  name = "fiap-cluster"
}
