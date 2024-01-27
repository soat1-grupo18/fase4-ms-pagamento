terraform {
  backend "s3" {
    bucket         = "289389227463-terraform-backend"
    key            = "github/soat1-grupo18/fase4-ms-pedido"
    dynamodb_table = "289389227463-terraform-backend"
    region         = "sa-east-1"
  }
}

provider "aws" {
  region = var.aws_region
  default_tags {
    tags = {
      terraform = "true"
    }
  }
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.fiap_store.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.fiap_store.certificate_authority[0].data)
  token                  = data.aws_eks_cluster_auth.fiap_store.token
}

data "aws_caller_identity" "current" {}

data "aws_region" "current" {}
